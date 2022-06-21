package lt.bit.products.store.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class Pricer {

  private final static Logger LOG = LoggerFactory.getLogger(Pricer.class);

  @Scheduled(fixedRateString = "5000")
  void recalculateProductPrice() {
    LOG.info("Pricer job started");
    LOG.info("Recalculating product prices...");

    // TODO

    LOG.info("Product prices updated.");
  }
}
